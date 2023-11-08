/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */
$(document).ready(main);

var contador = 1;
var ancho = $(document).width();

function main (){
	$('.menu_bar').click(function(){
		
		if (contador === 1){
			$('nav').animate({
				left: '0'
			});
			contador = 0;
		} else {
			contador = 1;
			$('nav').animate({
				left: '-100%'
			});
		};
		
	});

	$('.submenu').click(function(){
		$(this).children('.children').slideToggle();
	});

};