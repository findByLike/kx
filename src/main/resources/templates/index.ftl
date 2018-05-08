<!DOCTYPE html>  
<html lang="en">  
<head>  
    <meta charset="UTF-8">  
    <title>Title</title>  
    <script src="/js/echarts.min.js"></script>
    <script src="/js/jquery-3.3.1.min.js"></script>
</head>  
<body>  
<input type="text" id="date1" value="2017/04/11"/>
<input type="text" id="date2" value="2017/05/11"/>
<button>查询</button>
<div id="main" style="width: 1200px;height:600px;"></div>
    <script type="text/javascript">
        var setLine=function(series,xdata,min){
        	 	var myChart = echarts.init(document.getElementById('main'));
		        var option = {
		             dataZoom: [
				        {
				            id: 'dataZoomX',
				            type: 'slider',
				            xAxisIndex: [0],
				            filterMode: 'filter'
				        },
				        {
				            id: 'dataZoomY',
				            type: 'slider',
				            yAxisIndex: [0],
				            filterMode: 'empty'
				        }
				    ],
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
				        min: min
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
		      setLine(series,data.date,data.min);
		    });
		  });
		});
    </script> 
</body>  
</html> 