const userobject ={
	
	init: function(){
		const btnSave= document.getElementById('btn-save');
		const btnModify = document.getElementById('btn-modify');
		const btnDelete = document.getElementById('btn-delete');
		
		if(btnSave){
			btnSave.addEventListener('click',(e)=>{
				e.preventDefault(); /* summit 버튼이라서 초기화되기때문에 안되게 기본설정*/
				
				this.insertUser();
			})
		}
		
		if(btnModify){
			btnModify.addEventListener('click',(e)=>{
				e.preventDefault();
				this.updateUser();
			})
		}
		if(btnDelete){
			btnDelete.addEventListener('click',(e)=>{
				e.preventDefault();
				this.deleteUser();
			})
		}
		
	},
	
	
	insertUser: function() {
		const user = {
			username : document.getElementById("username").value,
			password : document.getElementById("password").value,
			email : document.getElementById("email").value
		}
		fetch('/auth/join', {
			method: 'POST',
			headers: {
				'Content-Type' : 'application/json; charset=UTF-8' // 기본값이라 궂이 안넣어도댐
			},
			body: JSON.stringify(user)
		}).then(response => response.json())
		.then(json => {
			
			if(json.status == 200){
				alert(json.data);
				
				window.location.href="/";
			}else{
				let msg='';
				let errors = json.data;
				
				if(typeof errors == 'string')
					msg = errors;
				
				if(errors.username != null)
					msg += errors.username + '\n'
				if(errors.password !=null)
					msg += errors.password + '\n'
				if(errors.email != null)
					msg += errors.email
				
				alert(msg)
			}
				
			
		}).catch(error=>{
			console.error("회원 가입 중 오류 발생", error);
		})
		
		
	},
	updateUser(){
		const user = {
			id:document.getElementById('id').value,
			username:document.getElementById('username').value,
			password:document.getElementById('password').value,
			email:document.getElementById('email').value,
		}
		
		fetch('/auth/info',{
			method:'POST',
			body:JSON.stringify(user),
			headers:{
				'Content-Type' : 'application/json; charset=utf-8'
			}
		}).then(response => response.josn())
		.then(result =>{
			alert(result.data);
			
			window.location.href="/auth/info";
		}).catch(error=>{
			console.log('수정 요청 중 오류발생',error)
		})
	},
	
	deleteUser : function() {
		const id = document.getElementById('id').value;
		fetch(`/auth/user?id=${id}`, {
			method: 'DELETE'
		}).then(reponse => reponse.json())
		.then(result =>{
			alert(result.date);
			
			window.location.href = "/";
		}).catch(error =>{
			console.log("탈퇴처리중 오류", error);
		})
	}

}


userobject.init();