<!DOCTYPE html>  
<html lang="en">  
<head>  
    <meta charset="UTF-8">  
    <title>Title</title>  
    <script src="/js/echarts.min.js"></script>
    <script src="/js/jquery-3.3.1.min.js"></script>
</head>  
<body>  
<input type="text" id="date1" value="2017/11"/>
<input type="text" id="date2" value="2018/3"/>
<button>查询</button>
<div id="main" style="width: 1800px;height:600px;"></div>
    <script type="text/javascript">
        var setLine=function(series,xdata){
        	 	var myChart = echarts.init(document.getElementById('main'));
		        var option = {
		            title: {
		                text: 'Kx线'
		            },
		             tooltip: {
				        trigger: 'axis'
				    },
		            legend: {
		                data:['open','close','high','low']
		            },
		            xAxis: {
		                data: xdata
		            },
		             yAxis: {
				        min: 3200
				    },
		            series: series
		        };
		        myChart.setOption(option);
        }
        
        $(document).ready(function(){
		  $("button").click(function(){
		  var params={
		      date1:$("#date1").val(),
		      date2:$("#date2").val()
		  };
		  
		    $.get("/getkx",params,function(data,status){
		      console.log(data);
		      var series=[];
		      series.push({name:'open',type:'line',data:data.open});
		      series.push({name:'close',type:'line',data:data.close});
		      series.push({name:'high',type:'line',data:data.high});
		      series.push({name:'low',type:'line',data:data.low});
		      setLine(series,data.date);
		    });
		  });
		});
    </script> 
</body>  
</html> 