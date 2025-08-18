$(document).ready(function() {
  $('#content').summernote({
	height:400
	
  });
});

const postObject = {
	
	init : function(){
		const btnInsert = document.getElementById('btn-insert');
		const btnUpdate = document.getElementById('btn-update');
		const btnDelete = document.getElementById('btn-delete');
		
	
		
		if(btnInsert){
			btnInsert.addEventListener('click', (e)=>{
				e.preventDefault();
				
				if(confirm('정말로 수정하시겠습니까?'))
				
				this.insertPost();
			})
		}	
		if(btnUpdate){
			btnUpdate.addEventListener('click',(e)=>{
				e.preventDefault();
				
				this.updatePost();
			})
		}
		if(btnDelete){
			btnDelete.addEventListener('click',(e)=>{
				e.preventDefault();
				
				this.deletePost();
			})
		}
		
		
	},
	
	insertPost: function(){
		const post ={
			title: document.getElementById('title').value,
			content:document.getElementById('content').value,
		}
		fetch('/post',{
			method: 'POST',
			headers: {
				'Content-Type' : 'application/json; charset=utf-8'
			},
			body: JSON.stringify(post)
		}).then(response => response.json())
		.then(result =>{
			alert(result.data);
			
			window.location.href="/";
		}).catch(error =>{
			console.log(error);
		})
	},
	
	updatePost: function(){
		
		const post = {
			id : document.getElementById('id').value,
			title:document.getElementById('title').value,
			content: document.getElementById('content').value,
		}
		fetch('/post',{
			method: 'PUT',
			headers:{
				'Content-Type' : 'application/json; charset=utf-8'
			},
			body:JSON.stringify(post)
		}).then(response => response.json())
		.then(result =>{
			alert(result.data);
			
			window.location.href="/";
		}).catch(error =>{
			console.log(error);
		})
	},
	
	deletePost: function(){
		const id = document.querySelector('.id').innerHTML;
		
		fetch(`/post/${id}`,{
			method:'DELETE',
						
		})
		.then(response => response.json())
		.then(result =>{
			alert(result.data);
			
			window.location.href="/";
		}).catch(error =>{
			console.log(error);
		})
	}
	
	
}




postObject.init();