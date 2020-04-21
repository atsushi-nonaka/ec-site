$(function(){
	
	//郵便番号から住所を自動検索する関数
	$('#findAddress').on('click',function(){
		AjaxZip3.zip2addr('zipcode','','address','address');
	});
	
	$('#findDestinationAddress').on('click',function(){
		AjaxZip3.zip2addr('destinationZipcode','','destinationAddress','destinationAddress');
	});
	
	//商品金額
	$('.size').on('change', function(){
		calcName();
	});
	
	$('.topping').on('change', function(){
		calcName();
	});
	
	$('.quantity').on('change', function(){
		calcName();
	});
	
	function calcName(){
		let sizePrice = 0;
		let toppingPrice = 0;
		let totalPrice = 0;
		let size = $('.size:checked').val();
		let quantity = $('.quantity option:selected').val(); 
		let topping = $('.topping:checked').length;
		if(size === 'M'){
			sizePrice = $('#priceM').val();
			toppingPrice = 200 * topping;
		}else if(size === 'L'){
			sizePrice = $('#priceL').val();
			toppingPrice = 300 * topping;
		}
		totalPrice = quantity * (parseInt(sizePrice) + toppingPrice);
		$('#totalPrice').html(totalPrice.toLocaleString());
	};
	
	//クレジット決済について
	$(".inputCreditInformation").hide();
	
	$("#card").on("change", function(){
		$(".inputCreditInformation").show();
	});
	
	$("#cash").on("change", function(){
		$(".inputCreditInformation").hide();
	});
	
	//クレジットカード番号を正規表現から特定する機能
	$("#creditNumber").on("keyup",function(){
		$.ajax({
			url : "http://localhost:8080/check_credit_number",
			type : "POST",
			dataType : "json",
			data : {
				creditNumber : $("#creditNumber").val()
			},
			async:true
		}).done(function(data){
			console.log(data);
			console.dir(JSON.stringify(data));
			$("#companyName").text(data.companyName);
		}).fail(function(XMLHttpRequest, textStatus,errorThrown){
			alert("エラーが発生しました");
			console.log("XMLHttpRequest : " + XMLHttpRequest.status);
			console.log("textStatus : " + textStatus);
			console.log("errorThrown : " + errorThrown.message);
		})
	});
		
});

	//パスワードをテキスト＝パスワード表示に変更する機能
	const pwd = document.getElementById('inputPassword');
	const pwdCheck = document.getElementById('password-check');
	pwdCheck.addEventListener('change', function() {
	    if(pwdCheck.checked) {
	        pwd.setAttribute('type', 'text');
	    } else {
	        pwd.setAttribute('type', 'password');
	    }
	}, false);