var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "446403",
        "ok": "446403",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "1",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "2419",
        "ok": "2419",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "55",
        "ok": "55",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "71",
        "ok": "71",
        "ko": "-"
    },
    "percentiles1": {
        "total": "43",
        "ok": "43",
        "ko": "-"
    },
    "percentiles2": {
        "total": "76",
        "ok": "76",
        "ko": "-"
    },
    "percentiles3": {
        "total": "162",
        "ok": "162",
        "ko": "-"
    },
    "percentiles4": {
        "total": "290",
        "ok": "290",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 446023,
    "percentage": 100
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 199,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 181,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "123.932",
        "ok": "123.932",
        "ko": "-"
    }
},
contents: {
"req_http-setting--q-ccdf5": {
        type: "REQUEST",
        name: "HTTP setting: Quote",
path: "HTTP setting: Quote",
pathFormatted: "req_http-setting--q-ccdf5",
stats: {
    "name": "HTTP setting: Quote",
    "numberOfRequests": {
        "total": "446403",
        "ok": "446403",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "1",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "2419",
        "ok": "2419",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "55",
        "ok": "55",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "71",
        "ok": "71",
        "ko": "-"
    },
    "percentiles1": {
        "total": "43",
        "ok": "43",
        "ko": "-"
    },
    "percentiles2": {
        "total": "76",
        "ok": "76",
        "ko": "-"
    },
    "percentiles3": {
        "total": "162",
        "ok": "162",
        "ko": "-"
    },
    "percentiles4": {
        "total": "290",
        "ok": "290",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 446023,
    "percentage": 100
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 199,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 181,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "123.932",
        "ok": "123.932",
        "ko": "-"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
