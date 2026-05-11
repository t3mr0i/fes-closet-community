#import "CDPGate.h"
#import "CDPNativeBridgeMsgUtils.h"

@interface FESUUID : CDPGate
@end

@implementation FESUUID

- (void)generate
{
    CDPMethodContext* context = [self getContextWithSendResultStatus:NO];
    NSString* uuid = [[NSUUID UUID] UUIDString];
    [self resolveParams:context withParams:@[uuid]];
}

@end
