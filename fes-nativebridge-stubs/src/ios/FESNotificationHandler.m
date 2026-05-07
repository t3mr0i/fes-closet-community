#import "CDPGate.h"
#import "CDPNativeBridgeMsgUtils.h"

@interface FESNotificationHandler : CDPGate
@end

@implementation FESNotificationHandler

- (void)startListening
{
    CDPMethodContext* context = [self getContextWithSendResultStatus:NO];
    [self resolveParams:context];
}

- (void)clear
{
    CDPMethodContext* context = [self getContextWithSendResultStatus:NO];
    [self resolveParams:context];
}

@end
