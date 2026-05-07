/**
 * FESUUID.m
 * Stub for Sony FES NativeBridge UUID on iOS.
 * Called by ensureOwnerId() at startup to generate a persistent device UUID.
 * Returns a standard UUID string via NSUUID.
 */

#import "CDPGate.h"
#import "CDPNativeBridgeMsgUtils.h"

@interface FESUUID : CDPGate
@end

@implementation FESUUID

- (void)generate:(CDPMethodContext*)context
{
    NSString* uuid = [[NSUUID UUID] UUIDString];
    [self resolveParams:context withParams:@[uuid]];
}

@end
