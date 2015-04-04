import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {

  @Override public void channelActive(final ChannelHandlerContext ctx) throws Exception {
    ByteBuf buffer = ctx.alloc().buffer(4);
    buffer.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));

    final ChannelFuture channelFuture = ctx.writeAndFlush(buffer);
    channelFuture.addListener(new ChannelFutureListener() {
      @Override public void operationComplete(ChannelFuture future) throws Exception {
        assert channelFuture == future;
        ctx.close();
      }
    });
    channelFuture.addListener(ChannelFutureListener.CLOSE);
  }

  @Override public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
      throws Exception {
    cause.printStackTrace();
    ctx.close();
  }
}
