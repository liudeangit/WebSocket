<template>
  <div>
    <button @click="createSocket">建立连接</button>
  </div>
</template>
<script>
export default {
  data() {
    return {
    };
  },
  mounted() {},
  methods: {
    createSocket() {
      let uuid = 123;
      var socket;
      if (typeof WebSocket == "undefined") {
        console.log("您的浏览器不支持WebSocket");
      } else {
        //实现化WebSocket对象，指定要连接的服务器地址与端口  建立连接
        let socketUrl = "ws://192.168.3.254:8010/websocket/" + uuid;
        if (socket != null) {
          socket.close();
          socket = null;
        }
        // 开启一个websocket服务
        socket = new WebSocket(socketUrl);
        //打开事件
        socket.onopen = function () {
          console.log("Socket 已打开");
          //socket.send("这是来自客户端的消息" + location.href + new Date());
        };
        //获得消息事件
        //浏览器端收消息，获得从服务端发送过来的文本消息
        socket.onmessage = function (event) {
          console.log("返回了");
          console.log(event);
          var result = JSON.parse(event.data);
          alert(result.data);
        };
        //关闭事件
        socket.onclose = function () {
          console.log("Socket已关闭");
        };
        //发生了错误事件
        socket.onerror = function () {
          console.log("Socket发生了错误");
        };
        //窗口关闭
        $(window).unload(function (event) {
          socket.close();
        });
      }
      return socket;
    },
  },
};
</script>