<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/cschart.css">
</head>
<body>
    <div id="demobox">
        <div id="csbox">
            <div id="option">
                <input id="oneD" name="1D" type="button" value="1D"/>
                <input id="oneM" name="1M" type="button" value="1M"/>
                <input id="threeM" name="3M" type="button" value="3M" />
                <input id="sixM" name="6M" type="button" value="6M" />
                <input id="oneY" name="1Y" type="button" value="1Y" />
                <input id="twoY" name="2Y" type="button" value="2Y" />
            </div>
            <div id="infobar">
                <div id="infodate" class="infohead"></div>
                <div id="infoopen" class="infobox"></div>
                <div id="infohigh" class="infobox"></div>
                <div id="infolow" class="infobox"></div>
                <div id="infoclose" class="infobox"></div>
            </div>
            <div id="chart1"></div>
        </div> <!-- csbox -->
    </div> <!-- demobox -->
    <script src="//d3js.org/d3.v3.min.js" charset="utf-8"></script>
    <script src="//d3js.org/d3-queue.v3.min.js"></script>
    <script src="//d3js.org/topojson.v1.min.js"></script>
    <script src="js/cschart.js"></script>
    <script src="js/csbars.js"></script>
    <script src="js/csheader.js"></script>
    <script src="js/csdataprep.js"></script>
    <script src="js/csmain.js"></script>
    <script>
	setTimeout("INIT_CHART_WP('<%=request.getParameter("symbol")%>','<%=request.getParameter("range")%>');",1000);
	</script>
    
</body>
</html>
