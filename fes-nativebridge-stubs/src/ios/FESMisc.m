/**
 * FESMisc.m
 * Stub for Sony FES NativeBridge Misc on iOS.
 * The app already handles ERROR_METHOD_NOT_FOUND gracefully for both methods,
 * but returning success avoids the error path and gives hooks for real native
 * implementations later.
 */

#import "CDPGate.h"
#import "CDPNativeBridgeMsgUtils.h"

@interface FESMisc : CDPGate
@end

@implementation FESMisc

- (void)changeStatusBarColor:(CDPMethodContext*)context
{
    [self resolveParams:context];
}

- (void)listenToChangeFocus:(CDPMethodContext*)context
{
    [self resolveParams:context];
}

@end
