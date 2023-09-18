//
//  IosTokenPreference.swift
//  AjarinIos
//
//  Created by Darren Thiores on 18/09/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class IosTokenPreferences: TokenPreferences {
    let keyChain = KeychainSwift.shared
    
    func getAccessToken() -> String {
        return keyChain.get("access_token") ?? ""
    }
    
    func getRefreshToken() -> String {
        return keyChain.get("refresh_token") ?? ""
    }
    
    func resetToken() {
        keyChain.set("", forKey: "access_token")
        keyChain.set("", forKey: "refresh_token")
    }
    
    func setToken(accessToken: String, refreshToken: String) {
        keyChain.set(accessToken, forKey: "access_token")
        keyChain.set(refreshToken, forKey: "refresh_token")
    }
}
