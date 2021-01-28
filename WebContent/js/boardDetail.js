/**
 * 
 */

function boardDelete(bno){
	$.ajax({
		type :"post",
		url : "/lectureEvaluation/boardServlet?cmd=delete1&bno="+bno,
		dataType: "json"
	}).done(function(result){
		console.log("result" + result)
		if(result.statusCode ==1){
			alert("삭제가 완료 되었습니다.")
			location.href = "/lectureEvaluation/boardServlet?cmd=boardList";
		}else if(result.statusCode == -1){
			alert("로그인이 필요합니다.");
		}else if(result.statusCode == 0){
			alert("권한이 없습니다.")
		}
	})
	
	
}