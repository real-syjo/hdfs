/* Globle variables */
var totalFileCount, fileUploadCount, fileSize;

	//파일업로드 시작
function startUploading() {
	var files = document.getElementById('files').files;
	if (files.length == 0) {
		alert("Please choose at least one file and try.");
		return;
	}
	fileUploadCount = 0;
	prepareProgressBarUI(files);
	
		fileInfo(files);
		uploadFile();
		saveFileInfo(files);
}

//파일 크기 및 합
function fileInfo(files) {
	fileCnt = files.length
	var arr = [];
	for (var i = 0; i < fileCnt; i++) {
		arr.push(files[i].size)
	}
	fileSum = arr.reduce((a, b) => a + b, 0);

	saveBoardInfo(fileSum, fileCnt);
}

//게시물 저장
function saveBoardInfo(sum, cnt) {
		axios.post('/saveBoardInfo', {
			filesum: sum,
			filecnt: cnt,
		}).then(res => {
		}).catch(error => {
			console.log(error)
		});
	}

//파일정보 저장
function saveFileInfo(files) { 

	for (var i = 0; i < files.length; i++) {
		axios.post('/saveFileInfo', {
			filesize: files[i].size,
			filenm: files[i].name,
			filetype: files[i].type
		}).then(res => {
			if(res.data){
				console.log(res.data);
			}
		}).catch(error => {
			console.log(error)
		});
	}
}

//node 서버로 파일업로드
function uploadFile() {
	var name = document.getElementById('name').innerText;
	var dir = name.split('@')[0];
	var url = "//52.78.34.69:3000/upload?name=" + dir;
	var file = document.getElementById('files').files[fileUploadCount];

	fileSize = file.size;
	var fd = new FormData();
	fd.append("fileList", file);

	axios.post(url, fd, {
		headers: {
			"Content-Type": "multipart/form-data",
		},
		onUploadProgress,
	}).then(response => {
		onUploadComplete();
	}).catch(error => {
		onUploadError();
	});
}



//progress bar UI
function prepareProgressBarUI(files) {
	totalFileCount = files.length;
	var $tbody = $("#progress-bar-container").find("tbody");
	$("#upload-header-text").html("1 of " + totalFileCount + " file(s) is uploading");
	for (var i = 0; i < totalFileCount; i++) {
		var $tr = $("#progress-bar-container").find("tr");
		var fsize = parseFileSize(files[i].size);
		var fname = files[i].name;
		var bar = '<tr id="progress-bar-' + $tr.length + '"><td style="width:75%"><div class="filename">' + fname + '</div>'
			+ '<div class="progress"><div class="progress-bar progress-bar-striped active" style="width:0%"></div></div></td>'
			+ '<td  style="width:25%"><span class="size-loaded"></span> ' + fsize + ' <span class="percent-loaded"></span></td></tr>';
		$tbody.append(bar);
	}
	$("#upload-status-container").show();
}

//파일 사이즈
function parseFileSize(size) {
	var precision = 1;
	var factor = Math.pow(10, precision);
	size = Math.round(size / 1024); //size in KB
	if (size < 1000) {
		return size + " KB";
	} else {
		size = Number.parseFloat(size / 1024); //size in MB
		if (size < 1000) {
			return (Math.round(size * factor) / factor) + " MB";
		} else {
			size = Number.parseFloat(size / 1024); //size in GB
			return (Math.round(size * factor) / factor) + " GB";
		}
	}
	return 0;
}


//progress bar 순서 작동
function onUploadProgress(e) {
	if (e.lengthComputable) {
		//파일선택시 progress 순차 실행
		var $tr=$("#progress-bar-container").find("tr");
		var trStart = $tr.length-totalFileCount;
		var total ;
		//최초1회 업로드
		if(trStart == 0){
			if(0<=fileUploadCount){
				total++;
				total = fileUploadCount;
			}
		}else{
		//추가 업로드
			if(0<=fileUploadCount){
				total++;
				total =$tr.length-totalFileCount+fileUploadCount;
			}
		}
		var percentComplete = parseInt((e.loaded) * 100	/ e.total);

		var pbar = $('#progress-bar-'+total);
		var bar=pbar.find(".progress-bar");
		var sLoaded=pbar.find(".size-loaded");
		var pLoaded=pbar.find(".percent-loaded");
		bar.css("width",percentComplete + '%');
		sLoaded.html(parseFileSize(e.loaded)+ " / ");
		pLoaded.html("("+percentComplete+ "%)");
	} else {
		alert('unable to compute');
	}
}

//파일업로드 완료
function onUploadComplete(e, error) {

	var pbar = $('#progress-bar-'+fileUploadCount);
	if(error){
		pbar.find(".progress-bar").removeClass("active").addClass("progress-bar-danger");
	}else{
		pbar.find(".progress-bar").removeClass("active");
		pbar.find(".size-loaded").html('<i class="fa fa-check text-success"></i> ');
	}
	
	fileUploadCount++;
	if (fileUploadCount < totalFileCount) {
		$("#upload-header-text").html((fileUploadCount+1)+" of "+totalFileCount+" file(s) is uploading");
		uploadFile();
	} else {
		$("#upload-header-text").html("File(s) uploaded successfully!");
	}
		//location.href="list";
}


//업로드 에러
function onUploadError(e) {
	console.error("Something went wrong!");startUploading()
	onUploadComplete(e,true);
}


