package vip.fastgo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import vip.fastgo.protocol.ProtocolMessageOuterClass;


@Slf4j
public class MessageProcessor extends SimpleChannelInboundHandler<ProtocolMessageOuterClass.ProtocolMessage> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {

        ProtocolMessageOuterClass.ProtocolMessage.Builder builder = ProtocolMessageOuterClass.ProtocolMessage.newBuilder();
        builder.setCmd(ProtocolMessageOuterClass.ProtocolMessage.CmdType.LOGIN);
        ProtocolMessageOuterClass.LoginData login = ProtocolMessageOuterClass.LoginData.newBuilder().setUser("username").setPwd("123456").build();
        builder.setLogin(login);

        ctx.writeAndFlush(builder.build());
    }

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        log.info("channel read {}", msg.toString());
//
//        ProtocolMessageOuterClass.ProtocolMessage.parseFrom(msg);
//    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ProtocolMessageOuterClass.ProtocolMessage protocolMessage) throws Exception {
        switch (protocolMessage.getCmd()) {
            case LOGIN:
                log.info("Login message");
                log.info(protocolMessage.toString());
                break;
            case LOGOUT:
                log.info("Logout message");
                break;
        }
    }
}
