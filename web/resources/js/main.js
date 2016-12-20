
    // var $menu = $(".conmenu-ul"), // кэшируем в переменную меню
    //     $links = $menu.find("a"); // кэшируем в переменную ссылки
    // console.log($menu)
    // $links.on("click", function() {
    //     $menu.children().removeClass("active"); // убираем класс у всех пунктов
    //     $(this).parent().addClass("active"); // добавляем к пункту, содержащему нажатую ссылку
    // });


    (function($) {
        $(function() {

            $('ul.conmenu-ul').each(function(i) {
                var storage = localStorage.getItem('tab' + i);
                if (storage) {
                    $(this).find('li').removeClass('active').eq(storage).addClass('active');

                }
            });

            $('ul.conmenu-ul').on('click', 'li:not(.active)', function() {
                $(this)
                    .addClass('active').siblings().removeClass('active');

                var ulIndex = $('ul.conmenu-ul').index($(this).parents('ul.conmenu-ul'));
                localStorage.removeItem('tab' + ulIndex);
                localStorage.setItem('tab' + ulIndex, $(this).index());
            });

        });
    })(jQuery);


// for (var i=0;i<tabs.length;i++){
// 	tabs[i].addEventListener("onclick",function(e){
// 		console.log('I am working');
//
// 		tabs[i].classList.add('active');
// 	});
// }
