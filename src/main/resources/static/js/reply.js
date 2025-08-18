
const replyObject = {
    init: function(){
        const btnSaveReply = document.getElementById('btn-save-reply')
        
        if(btnSaveReply){
            btnSaveReply.addEventListener('click', (e) => {
                e.preventDefault();
                this.insertReply(); // 댓글 등록 함수 호출 추가
            })
        }
    },

    insertReply: function(){
        alert('댓글 등록 요청');

        const id = document.getElementById('postId').value;
        const reply = {
            content: document.getElementById('reply-content').value
        }
        
        fetch(`/reply/${id}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(reply)
        }).then(response => response.json())
        .then(result => {
            alert(result.data);
            window.location.href = `/post/${id}`; // 템플릿 리터럴 수정
        }).catch(error => { // catch 구문 수정
            console.log(error);
        })
    }
}

replyObject.init();