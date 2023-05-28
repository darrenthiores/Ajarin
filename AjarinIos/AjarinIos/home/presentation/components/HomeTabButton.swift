import SwiftUI

struct HomeTabButton: View {
    let title: String
    let onClick: () -> Void
    let isSelected: Bool
    
    var body: some View {
        Button(title) {
            withAnimation { onClick() }
        }
        .frame(maxWidth: .infinity)
        .fontWeight(isSelected ? .bold : .regular)
        .buttonStyle(.plain)
    }
}

struct HomeTabButton_Previews: PreviewProvider {
    static var previews: some View {
        HomeTabButton(
            title: "User",
            onClick: {},
            isSelected: true
        )
    }
}
