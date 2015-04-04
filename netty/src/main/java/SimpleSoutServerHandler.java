import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import java.nio.charset.StandardCharsets;

public class SimpleSoutServerHandler extends ChannelInboundHandlerAdapter {
  @Override public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

    ByteBuf buffer = (ByteBuf) msg;
    try {
      System.out.println(buffer.toString(StandardCharsets.US_ASCII));
    } finally {
      ReferenceCountUtil.release(msg);
    }
  }

  @Override public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
      throws Exception {
    super.exceptionCaught(ctx, cause);
    ctx.close();
  }
}
