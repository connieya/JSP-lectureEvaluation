/**
 * 
 */

function boardDelete(bno){
	$.ajax({
		type :"post",
		url : "/lectureEvaluation/boardServlet?cmd=delete&bno="+bno,
		dataType: "json"
	}).done(function(result){
		console.log("result" + result)
		if(result.statusCode ==1){
			location.href = "/lectureEvaulatuon/boardServlet?cmd=boardList";
		}else{
			alert("삭제에 실패했습니다.");
		}
	})
	
	
}