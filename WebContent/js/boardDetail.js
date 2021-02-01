/**
 * 
 */
/**
 * 
 */


function addReply(data){
	
	var replyItem = `<li id="reply-${data.bno}" class="commentItem">`;
	replyItem += `<c:forEach var="reply">`
	replyItem += `<div class="comment_area" style="border-top: 2px solid #e5e5e5">`
	replyItem += `<div class="comment_box">`
	replyItem += `<div class="comment_nick_box">`
	replyItem += `<div class="comment_nick_info" id="comment_username">${data.userName}</div>`
	replyItem += `</div>`
	replyItem += `<div class="comment_text_box">`
	replyItem += `<p class="comment_text_box" id="comment_content">${data.content} </p>`
	replyItem += `</div>`
	replyItem += `<div class="comment_info_box">`
	replyItem +=`<span class="comment_info_date">${data.createDate}</span>`
	replyItem +=`<a href="" class="comment_info_button">답글쓰기</a>`
	replyItem +=`</div>`
	replyItem += `<c:if>`
	replyItem +=`<div class="comment_tool">`
	replyItem +=`<button class="comment_tool_button btn btn-register"  onclick="commentDelete()">삭제</button>`
	replyItem +=`</div>`
	replyItem +=`</c:if>`
	replyItem +=`</div>`
	replyItem +=`</div>  `
	replyItem +=`</c:forEach>`
	replyItem +=`</li> `
	
	
		$("#comment__list").prepend(replyItem)
				
}

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

function commentRegister(bno ) {
		var data = {
			userName : $("#userName").val(),
			bno : bno,
			content :$("#reply").val(),
			userNo:$("#userNo").val()
		}
		$.ajax({
			type: "post",
			url : "/lectureEvaluation/replyServlet?cmd=register",
			dataType : "json",
			data : JSON.stringify(data),
			contentType : "application/json; charset=utf-8"
			
		}).done(function(result){
			console.log(result)
			if(result.statusCode ==1){
				addReply(result.data)
				$("#reply").val("") 
			}else{
				alert("댓글 쓰기 실패")
			}
		})
		
	}


function commentDelete(rno){
		$.ajax({
			type: "post",
			url : "/lectureEvaluation/replyServlet?cmd=delete&rno="+rno,
			dataType : "json"
			
		}).done(function(result){
			console.log(result)
			if(result.statusCode ==1){
				console.log(result);
				$("#reply-"+rno).remove();
			}else{
				alert("댓글 삭제 실패")
			}
		})
		
	}
	




