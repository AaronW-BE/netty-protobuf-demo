package vip.fastgo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import vip.fastgo.protocol.ProtocolMessageOuterClass;


@Slf4j
public class MessageProcessor extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {

        ProtocolMessageOuterClass.ProtocolMessage.Builder builder = ProtocolMessageOuterClass.ProtocolMessage.newBuilder();
        builder.setCmd(ProtocolMessageOuterClass.ProtocolMessage.CmdType.LOGIN);
        ProtocolMessageOuterClass.LoginData login = ProtocolMessageOuterClass.LoginData.newBuilder().setUser("username").setPwd("password").build();
        builder.setLogin(login);

        ctx.writeAndFlush(builder.build());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        log.info("channel read {}", msg.toString());
    }
}
