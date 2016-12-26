// (function($) {
// 	$(function() {
//
//
// 		$('ul.conmenu-ul').each(function(i) {
// 			var storage = localStorage.getItem('tab' + i);
// 			if (storage) {
// 				$(this).find('li').removeClass('active').eq(storage).addClass('active');
//
// 			}
// 		});
//
//
//
// 		$('ul.conmenu-ul').on('click', 'li:not(.active)', function() {
// 			$(this)
// 				.addClass('active').siblings().removeClass('active');
//
// 			var ulIndex = $('ul.conmenu-ul').index($(this).parents('ul.conmenu-ul'));
// 			localStorage.removeItem('tab' + ulIndex);
// 			localStorage.setItem('tab' + ulIndex, $(this).index());
// 		});
//
// 	});
//
// 	$('.reset').on('click',function(){
// 		$('.form-control').val('');
// 	})
// })(jQuery);



$(document).ready(function(){
    $("#searchtext").keypress(function(e){
        if(e.keyCode==13){

        var text=$("#searchtext").val();

            var url = "/text?txt="+text;
            $(location).attr('href',url);

        }
    });

});