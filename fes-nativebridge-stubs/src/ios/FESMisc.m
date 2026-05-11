#import "CDPGate.h"
#import "CDPNativeBridgeMsgUtils.h"

@interface FESMisc : CDPGate
@end

@implementation FESMisc

- (void)changeStatusBarColor
{
    CDPMethodContext* context = [self getContextWithSendResultStatus:NO];
    [self resolveParams:context];
}

- (void)listenToChangeFocus
{
    CDPMethodContext* context = [self getContextWithSendResultStatus:NO];
    [self resolveParams:context];
}

@end
