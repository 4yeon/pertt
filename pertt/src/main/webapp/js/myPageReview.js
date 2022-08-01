$(function(){
	
	let member_num = $('#member_num').val();
	
	//내 리뷰 정보를 읽어와서 html 태그를 추가하는 ajax 
	function myReview (pageNum,sort){
		let sort = $('.sort').attr('data-num');
		$.ajax({
			url:'myPageReview.do',
			type:'post',
			data:{member_num:member_num,sort:sort},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인한 후에 이용할 수 있습니다.');
				}else if (param.result =='success'){
					alert('내가확인할 값'); 
					
				}
			},
			error:function(){
				alert('내 글 보기에서 네트워크 오류 발생');
			}	
				
	};
	
	
	
	myReview(member_num);
});