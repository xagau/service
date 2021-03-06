<!DOCTYPE html>
<meta charset="utf-8">
<style>

    body {
        font: 10px sans-serif;
    }

    text {
        fill: #000;
    }

    button {
        position: absolute;
        right: 20px;
        top: 440px;
        display: none;
    }

    path.line {
        fill: none;
        stroke: #000000;
        stroke-width: 1;
    }

</style>

<body onResize="refresh()">
<script src="./js/d3.v4.min.js"></script>
<script src="./js/techan.min.js"></script>
<script>

function vwidth(){
   return window.innerWidth 
       || document.documentElement.clientWidth 
       || document.body.clientWidth 
       || 0;
}

function vheight(){
   return window.innerHeight 
       || document.documentElement.clientHeight 
       || document.body.clientHeight 
       || 0;
}

    function toFormat(da){
        console.log("FMT:" + da);
        sda = new String(da);
        sda = sda.substring(sda.indexOf(":")-2, sda.length);
        sda = sda.substring(0, sda.indexOf(" ")-4);
        console.log("FMT:" + sda);
        return sda;
    }
    var symbol = '<%=request.getParameter("symbol")%>';
    var range = '<%=request.getParameter("range")%>';

    function refresh() { location.reload(); }


    var margin = {top: 20, right: 20, bottom: 30, left: 50},
    width = vwidth() - margin.left - margin.right,
    height = (vheight()) - margin.top - margin.bottom;

    var parseDate = d3.timeParse("%Y-%m-%d %H:%M:%S.%L"); //.parse; // d3.timeParse("%y-%b-%d");

    var x = techan.scale.financetime()
            .range([0, width])
            .outerPadding(0);

    var y = d3.scaleLinear()
            .range([height, 0]);
    
    
    var close = techan.plot.close()
            .xScale(x)
            .yScale(y);

    var xAxis = d3.axisBottom(x);

    var yAxis = d3.axisLeft(y);

    var svg = d3.select("body").append("svg")
            .attr("width", width + margin.left + margin.right)
            .attr("height", height + margin.top + margin.bottom)
            .append("g")
            .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

    d3.csv("./DATA?symbol=" + symbol + "&range=" + range, function(error, data) {
        var accessor = close.accessor();

        data = data.slice(0, 200).map(function(d) {
            return {
                date: parseDate(d.Date),
                open: +d.Open,
                high: +d.High,
                low: +d.Low,
                close: +d.Close,
                volume: +d.Volume
            };
        }).sort(function(a, b) { return d3.ascending(accessor.d(a), accessor.d(b)); });

        svg.append("g")
                .attr("class", "close");

        svg.append("g")
                .attr("class", "x axis")
                .attr("transform", "translate(0," + height + ")");

        svg.append("g")
                .attr("class", "y axis")
                .append("text")
                .attr("transform", "rotate(-90)")
                .attr("y", 6)
                .attr("dy", ".71em")
                .style("text-anchor", "end")
                .text("Price ($)");
        
            


        // Data to display initially
        draw(data.slice(0, data.length-20));
        // Only want this button to be active if the data has loaded
        //d3.select("button").on("click", function() { draw(data); }).style("display", "inline");
    
        y.domain([d3.min(data, function(d) { return d.value; }) *1.2, d3.max(data, function(d) { return d.value; }) *1.2 ]);

    });

    function draw(data) {
        x.domain(data.map(close.accessor().d));
        y.domain(techan.scale.plot.ohlc(data, close.accessor()).domain());

        svg.selectAll("g.close").datum(data).call(close);
        svg.selectAll("g.x.axis").call(xAxis);
        svg.selectAll("g.y.axis").call(yAxis);
    }

</script>

<body onResize="refresh()">
