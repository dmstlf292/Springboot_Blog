let index={
	init:function(){
		$("#btn-save").on("click", ()=>{ //functions(){} 을 사용하지 않고 ()=>{}사용하는 이유 : this를 바인딩 하기 위해
			this.save();
		});
		$("#btn-update").on("click", ()=>{ 
			this.update();
		});
	},
	
	save : function(){
		//alert('user의 save 함수 호출됨');
		let data ={
			username:$("#username").val(),
			password:$("#password").val(),
			email:$("#email").val(),
			
		};
		
		
		//console.log(data);
		//ajax 요청하기
        //ajax 호출시 default 가 비동기 호출이다.
        //ajax 통신을 이용해서 3개의 데이터를 json 으로 변경하여 insert 요청하기
        //ajax 가 통신을 성공하고 서버가 json 을 리턴해주면 자동으로 자바 오브젝트로 변환해준다 
		$.ajax({//javascript object 들어와야한다@@@@@
            //회원가입 수행 요청
            type:"POST",
            url:"/auth/joinProc",
            data : JSON.stringify(data),//이거시 바로 json 문자열이다@@@ + http body 데이터이다
            contentType:"application/json;charset=utf-8", //body 데이터가 어떤 타입인지 (MIME)
            //요청을 서버로해서 응답이 왔을때 기본적으로 모든 것이 문자열(생긴게 json 이라면) => javascript 오브젝트로 변경해준다
            dataType:"json" //요청을 서버로 해서 서버로부터 요청에 대한 응답이 왔을때 응답결과를 json으로 받는것 (응답이 오면 기본적으로 모든것이 String 문자열로 온다)
		}).done(function(resp){
            //성공
            alert("Success!")
            //alert(resp);
            console.log(resp);
            location.href="/";
        }).fail(function(error){
            //실패
            alert(JSON.stringify(error));
        }); //ajax통신을 이용해서 3개의 파라미터를 =데이터를 json 으로 변경하여 insert 요청하기 
	},

	update : function(){
		let data ={
			id:$("#id").val(),
			password:$("#password").val(),
			email:$("#email").val(),
			
		};
		
		$.ajax({
            type:"PUT",
            url:"/user",
            data : JSON.stringify(data),
            contentType:"application/json;charset=utf-8", 
            dataType:"json" 
		}).done(function(resp){
            alert("Success!")
            console.log(resp);
            location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        }); 
	}
}

index.init();