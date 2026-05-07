/**
 * FESLocalContentProvider.m
 * Stub for Sony FES NativeBridge LocalContentProvider on iOS.
 *
 * Returns an empty content list. The app handles zero local skins gracefully
 * and falls back to the store / generated catalog.
 */

#import "CDPGate.h"
#import "CDPNativeBridgeMsgUtils.h"

@interface FESLocalContentProvider : CDPGate
@end

@implementation FESLocalContentProvider

- (void)queryLocalContents:(CDPMethodContext*)context withArg:(NSNumber*)queryIndex withArg:(NSNumber*)queryLimit
{
    NSDictionary* payload = @{
        @"totalContentCount": @0,
        @"contents": @[]
    };
    [self resolveParams:context withParams:@[payload]];
}

- (void)queryThumbnailByKey:(CDPMethodContext*)context withArg:(NSString*)key
{
    [self resolveParams:context withParams:@[@""]];
}

- (void)queryImageSourceByKey:(CDPMethodContext*)context withArg:(NSString*)key
{
    [self resolveParams:context withParams:@[@""]];
}

@end
