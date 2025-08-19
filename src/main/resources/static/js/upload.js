const uploadForm = document.getElementById('uploadForm');

uploadForm.addEventListener('submit',(e)=>{
	e.preventDefault();
	
	const fileInput = document.getElementById('fileInput');
	const file = fileInput.files[0];
	
	const fd = new FormData();
	fd.append('file',file);
	
	fetch('/api/images',{
		method:'POST',
		body: fd
	}).then(response => response.json())
	.then(result=>{
		alert('이미지 등록 성공')
		
		location.reload();
	}).catch(error=>{
		alert('이미지 등록 실패')
		
		console.log(error)
	})
	
})