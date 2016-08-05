$(function(){
	$("#fullpage").fullpage({    //全屏滚动
		controlArrows:false,
		anchors:["page1","page2","page3"],
		menu:"#fullpageMenu",
		navigation:true,
		navigationPosition:"right",
		navigationTooltips:["简历","demo","联系我"],
		showActiveTooltip:true,
	});
	$(".section2").mouseover(function(){
		$(".demo-link").animate({opacity: 1},800);
	});
	$(".icn2").click(function(){
		alert("敬请期待！");
	});
});