# BlockDetectUI_Java

## 前言

对于代码中存在的UI线程耗时的操作，我们需要有一套检测机制，帮助我们定位耗时发生的位置。

## 两种检测方式

1，利用UI线程Looper打印的日志 2，利用Choreographer
'''
final Printer logging = me.mLogging; if (logging != null) { logging.println(">>>>> Dispatching to "
+ msg.target + " "

+ msg.callback + ": " + msg.what); }

        try {
            msg.target.dispatchMessage(msg);
            if (observer != null) {
                observer.messageDispatched(token, msg);
            }
            dispatchEnd = needEndTime ? SystemClock.uptimeMillis() : 0;
        } catch (Exception exception) {
            if (observer != null) {
                observer.dispatchingThrewException(token, msg, exception);
            }
            throw exception;
        } finally {
            ThreadLocalWorkSource.restore(origWorkSource);
            if (traceTag != 0) {
                Trace.traceEnd(traceTag);
            }
        }

        if (logging != null) {
            logging.println("<<<<< Finished to " + msg.target + " " + msg.callback);
        }

'''

BlockDetectUI_Java 我们的卡顿的原因就在于没有办法在16ms完成该完成的操作。 监控UI卡顿
