#import "CDPGate.h"
#import "CDPNativeBridgeMsgUtils.h"

@interface FESLocalContentProvider : CDPGate
@end

@implementation FESLocalContentProvider

// Called as queryLocalContents(queryIndex, queryLimit) — 2 JS args → selector queryLocalContents::
- (void)queryLocalContents:(NSNumber*)queryIndex :(NSNumber*)queryLimit
{
    CDPMethodContext* context = [self getContextWithSendResultStatus:NO];
    NSDictionary* payload = @{ @"totalContentCount": @0, @"contents": @[] };
    [self resolveParams:context withParams:@[payload]];
}

// Called as queryThumbnailByKey(key) — 1 JS arg → selector queryThumbnailByKey:
- (void)queryThumbnailByKey:(NSString*)key
{
    CDPMethodContext* context = [self getContextWithSendResultStatus:NO];
    [self resolveParams:context withParams:@[@""]];
}

// Called as queryImageSourceByKey(key) — 1 JS arg → selector queryImageSourceByKey:
- (void)queryImageSourceByKey:(NSString*)key
{
    CDPMethodContext* context = [self getContextWithSendResultStatus:NO];
    [self resolveParams:context withParams:@[@""]];
}

@end
