$(function(){
	//郵便番号から住所を自動検索する関数
	$("#findAddress").on("click",function(){
		AjaxZip3.zip2addr('zipcode','','address','address');
	});
});