//
//  SearchMerchantHeader.swift
//  Fulaby
//
//  Created by Darren Thiores on 09/04/23.
//

import SwiftUI

struct SearchMentorHeader: View {
    @Binding var searchText: String
    @FocusState var isFocused: Bool
    let filtered: Bool
    let onSearch: () -> Void
    let onFilter: () -> Void
    let onClearFilter: () -> Void
    
    var body: some View {
        HStack {
            TextField(
                "Search...",
                text: $searchText
            )
            .textFieldStyle(.roundedBorder)
            .focused($isFocused)
            .onSubmit {
                onSearch()
            }
            
            if filtered || !searchText.isEmpty || isFocused {
                Button {
                    if !isFocused {
                        onClearFilter()
                    }
                    
                    searchText = ""
                    isFocused = false
                } label: {
                    Image(systemName: "xmark")
                }
                .buttonStyle(.plain)
            }
            
            Button {
                onFilter()
            } label: {
                Image(systemName: "slider.vertical.3")
            }
            
            if isFocused {
                Button {
                    onSearch()
                    isFocused = false
                } label: {
                    Text("Search")
                        .foregroundColor(.purple)
                }
            }
        }
    }
}

struct SearchMentorHeader_Previews: PreviewProvider {
    static var previews: some View {
        SearchMentorHeader(
            searchText: .constant("Mixue"),
            filtered: false,
            onSearch: {},
            onFilter: {},
            onClearFilter: {}
        )
    }
}

