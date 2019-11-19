package com.example.cctms.util

class JsonDatakt {

  public  var code: Int = 0
    public  var msg: String =""
    public var count: Int = 0
    public  var data: Any=""
    public  var data1: Any=""
    public  var result: String=""
    public    var result2: String=""

    constructor(code: Int, msg: String, count: Int, data: Any, data1: Any) : super() {
        this.code = code
        this.msg = msg
        this.count = count
        this.data = data
        this.data1 = data1
    }

    constructor(code: Int, msg: String, count: Int, data: Any,
                result: String, result2: String, data1: Any) : super() {
        this.code = code
        this.msg = msg
        this.count = count
        this.data = data
        this.result = result
        this.result2 = result2
        this.data1 = data1
    }

    constructor() : super() {
        // TODO Auto-generated constructor stub
    }

    companion object {
        var SUCCESS = 0
        var ERRR = 1
    }
}