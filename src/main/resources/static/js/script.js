$(function(){
	//郵便番号から住所を自動検索する関数
	$('#findAddress').on('click',function(){
		AjaxZip3.zip2addr('zipcode','','address','address');
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
		}else{
			sizePrice = $('#priceL').val();
			toppingPrice = 300 * topping;
		}
		totalPrice = quantity * (parseInt(sizePrice) + toppingPrice);
		$('#totalPrice').html(totalPrice.toLocaleString());
	}
		
});