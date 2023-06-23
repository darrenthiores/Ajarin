//
//  PaymentMethodExtension.swift
//  AjarinIos
//
//  Created by Darren Thiores on 23/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension PaymentMethod {
    
    func getLogo() -> String {
        switch self.id {
        case "0":
            return "dana_logo"
        case "1":
            return "gopay_logo"
        case "2":
            return "shopee_logo"
        case "3":
            return "ovo_logo"
        default:
            return "ic_no_picture"
        }
    }
}
