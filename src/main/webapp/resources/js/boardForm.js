/**
 * 
 */

$("#btn").click(function() {
	//title, contents 데이터 유무 검증
	var title = $("#title").val();
	//var contents = $("#contents").summernote('code');
	
	var ch3 = true;
	$(".files").each(function() {
		if($(this).val()==""){
			ch3 = false;
		}
	});
	
	var ch1 = title !="";
	//var ch2 = $("#contents").summernote('isEmpty');
	
	if(ch1 && !ch2 && ch3){
		//form 전송(submit event 강제 발생)
		$("#frm").submit();
	}else {
		//submit event 종료
		alert("필수 요소는 다 입력하세요");
	}

/* 		var contents = $("#contents").val();
	console.log(title=='');
	console.log(contents=="");
	console.log(title.length);
	console.log(contents.length);
	
	console.log($("#contents").summernote('isEmpty')); */	

});
var cli = 0;

function setCli(c) {
	cli = cli+c;
}

$("#file").on("click", ".remove",function() {
	 $("#files").remove(); 
	 //or $("this").parent.remove(); 
	 cli--;
});

	
	$("#add").click(function() {
		if (cli<5) {
		$("#file").append('<div id="files"><input type="file" class="form-control files" name="files"><i class="glyphicon glyphicon-remove remove"></i></div>');
		cli++;
		}else{
			alert("더이상 추가못햄");
		}

	});

	//$(선택자).action();
	/*$("#contents").summernote({
		  height: 300,                 // set editor height
		  minHeight: null,             // set minimum height of editor
		  maxHeight: null, 
		  focus: true,
		  callbacks:{
			  onImageUpload: function(file) {
				console.log("upload")
			}
		  }// set maximum height of editor                 // set focus to editable area after initializing summernote
		});*/
