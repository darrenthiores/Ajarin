import SwiftUI

struct ExpandableText: View {
    let text: String
    
    @State private var isExpanding: Bool = false
    @State private var isTruncated: Bool = false
    
    var body: some View {
        VStack(alignment: .leading) {
            Text(text)
                .font(.subheadline)
                .padding(.trailing)
                .lineLimit(isExpanding ? nil : 3)
                .background(
                    Text(text)
                        .font(.subheadline)
                        .lineLimit(3)
                        .background(
                            GeometryReader { displayGeo in
                                ZStack {
                                    Text(text)
                                        .font(.subheadline)
                                        .background(
                                            GeometryReader { fullGeo in
                                                Color.clear.onAppear {
                                                    isTruncated = fullGeo.size.height > displayGeo.size.height + CGFloat(30)
                                                }
                                            }
                                        )
                                }
                                .frame(height: .greatestFiniteMagnitude)
                            }
                        )
                        .hidden()
                )
            
            HStack {
                Spacer()
                
                if isTruncated {
                    Text(isExpanding ? "less" : "more")
                        .font(.subheadline)
                        .lineLimit(3)
                        .padding(.trailing)
                        .foregroundColor(.blue)
                        .onTapGesture {
                            isExpanding.toggle()
                        }
                }
            }
        }
    }
}

struct ExpandableText_Previews: PreviewProvider {
    static var previews: some View {
        ExpandableText(
            text: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
        )
    }
}

