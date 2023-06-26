//
//  BankExtension.swift
//  AjarinIos
//
//  Created by Darren Thiores on 26/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension Bank {
    func getImageName() -> String {
        switch bankId {
        case "1":
            return "bca_icon"
        case "2":
            return "bni_icon"
        case "3":
            return "bri_icon"
        case "4":
            return "mandiri_icon"
        case "5":
            return "bsi_icon"
        case "6":
            return "commbank_icon"
        default:
            return "ic_no_picture"
        }
    }
}
