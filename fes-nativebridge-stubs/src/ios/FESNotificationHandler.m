/**
 * FESNotificationHandler.m
 * Stub for Sony FES NativeBridge NotificationHandler on iOS.
 * The app guards all notification calls with h.Mobile (platform check),
 * so this class is never invoked in practice — it exists so cdp-nativebridge
 * doesn't return ERROR_CLASS_NOT_FOUND if the guard ever misses.
 */

#import "CDPGate.h"
#import "CDPNativeBridgeMsgUtils.h"

@interface FESNotificationHandler : CDPGate
@end

@implementation FESNotificationHandler

- (void)startListening:(CDPMethodContext*)context
{
    [self resolveParams:context];
}

- (void)clear:(CDPMethodContext*)context
{
    [self resolveParams:context];
}

@end
