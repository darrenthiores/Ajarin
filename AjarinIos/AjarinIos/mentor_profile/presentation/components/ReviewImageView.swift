import SwiftUI

struct ReviewImageView: View {
    let imageUrls: [String]
    let geo: GeometryProxy
    let onImageClick: (Int) -> Void
    
    var body: some View {
        if imageUrls.count == 1 {
            Button {
                onImageClick(0)
            } label: {
                AsyncImage(
                    url: URL(string: imageUrls[0]),
                    content: { image in
                        image
                            .resizable()
                            .aspectRatio(contentMode: .fill)
                            .frame(
                                width: geo.size.width - 40,
                                height: geo.size.width - 40
                            )
                    },
                    placeholder: {
                        ZStack {
                            ProgressView()
                                .frame(alignment: .center)
                        }
                        .frame(
                            width: geo.size.width - 40,
                            height: geo.size.width - 40
                        )
                        .background(.ultraThinMaterial)
                    }
                )
                .cornerRadius(8)
            }
            .buttonStyle(.plain)
        }
        
        if imageUrls.count == 2 {
            HStack(spacing: 4) {
                Button {
                    onImageClick(0)
                } label: {
                    AsyncImage(
                        url: URL(string: imageUrls[0]),
                        content: { image in
                            image
                                .resizable()
                                .aspectRatio(contentMode: .fill)
                                .frame(
                                    width: geo.size.width / 2 - 2 - 20,
                                    height: geo.size.width / 2 - 2 - 20
                                )
                        },
                        placeholder: {
                            ZStack {
                                ProgressView()
                                    .frame(alignment: .center)
                            }
                            .frame(
                                width: geo.size.width / 2 - 2 - 20,
                                height: geo.size.width / 2 - 2 - 20
                            )
                            .background(.ultraThinMaterial)
                        }
                    )
                    .cornerRadius(8)
                }
                .buttonStyle(.plain)
                
                Button {
                    onImageClick(1)
                } label: {
                    AsyncImage(
                        url: URL(string: imageUrls[1]),
                        content: { image in
                            image
                                .resizable()
                                .aspectRatio(contentMode: .fill)
                                .frame(
                                    width: geo.size.width / 2 - 2 - 20,
                                    height: geo.size.width / 2 - 2 - 20
                                )
                        },
                        placeholder: {
                            ZStack {
                                ProgressView()
                                    .frame(alignment: .center)
                            }
                            .frame(
                                width: geo.size.width / 2 - 2 - 20,
                                height: geo.size.width / 2 - 2 - 20
                            )
                            .background(.ultraThinMaterial)
                        }
                    )
                    .cornerRadius(8)
                }
                .buttonStyle(.plain)
            }
        }
        
        if imageUrls.count == 3 {
            HStack(spacing: 4) {
                Button {
                    onImageClick(0)
                } label: {
                    AsyncImage(
                        url: URL(string: imageUrls[0]),
                        content: { image in
                            image
                                .resizable()
                                .aspectRatio(contentMode: .fill)
                                .frame(
                                    width: geo.size.width / 2 - 2 - 20,
                                    height: geo.size.width / 2 - 2 - 20
                                )
                        },
                        placeholder: {
                            ZStack {
                                ProgressView()
                                    .frame(alignment: .center)
                            }
                            .frame(
                                width: geo.size.width / 2 - 2 - 20,
                                height: geo.size.width / 2 - 2 - 20
                            )
                            .background(.ultraThinMaterial)
                        }
                    )
                    .cornerRadius(8)
                }
                .buttonStyle(.plain)
                
                VStack(spacing: 4) {
                    Button {
                        onImageClick(1)
                    } label: {
                        AsyncImage(
                            url: URL(string: imageUrls[1]),
                            content: { image in
                                image
                                    .resizable()
                                    .aspectRatio(contentMode: .fill)
                                    .frame(
                                        width: geo.size.width / 2 - 2 - 20,
                                        height: geo.size.width / 2 / 2 - 4 - 10
                                    )
                            },
                            placeholder: {
                                ZStack {
                                    ProgressView()
                                        .frame(alignment: .center)
                                }
                                .frame(
                                    width: geo.size.width / 2 - 2 - 20,
                                    height: geo.size.width / 2 / 2 - 4 - 10
                                )
                                .background(.ultraThinMaterial)
                            }
                        )
                        .cornerRadius(8)
                    }
                    .buttonStyle(.plain)
                    
                    Button {
                        onImageClick(2)
                    } label: {
                        AsyncImage(
                            url: URL(string: imageUrls[2]),
                            content: { image in
                                image
                                    .resizable()
                                    .aspectRatio(contentMode: .fill)
                                    .frame(
                                        width: geo.size.width / 2 - 2 - 20,
                                        height: geo.size.width / 2 / 2 - 4 - 10
                                    )
                            },
                            placeholder: {
                                ZStack {
                                    ProgressView()
                                        .frame(alignment: .center)
                                }
                                .frame(
                                    width: geo.size.width / 2 - 2 - 20,
                                    height: geo.size.width / 2 / 2 - 4 - 10
                                )
                                .background(.ultraThinMaterial)
                            }
                        )
                        .cornerRadius(8)
                    }
                    .buttonStyle(.plain)
                }
            }
        }
        
        if imageUrls.count >= 4 {
            WrappingHStack(
                horizontalSpacing: 4,
                verticalSpacing: 4
            ) {
                ForEach(1...4, id: \.self) { index in
                    Button {
                        onImageClick(index-1)
                    } label: {
                        AsyncImage(
                            url: URL(string: imageUrls[index-1]),
                            content: { image in
                                image
                                    .resizable()
                                    .aspectRatio(contentMode: .fill)
                                    .frame(
                                        width: geo.size.width / 2 - 8 - 20,
                                        height: geo.size.width / 2 - 8 - 20
                                    )
                            },
                            placeholder: {
                                ZStack {
                                    ProgressView()
                                        .frame(alignment: .center)
                                }
                                .frame(
                                    width: geo.size.width / 2 - 8 - 20,
                                    height: geo.size.width / 2 - 8 - 20
                                )
                                .background(.ultraThinMaterial)
                            }
                        )
                        .cornerRadius(8)
                        .overlay {
                            if index - 1 == 3 && imageUrls.count > 4 {
                                ZStack {
                                    Color.black
                                        .opacity(0.2)
                                    
                                    Text("+ \(imageUrls.count - 4)")
                                        .foregroundColor(.white)
                                        .fontWeight(.bold)
                                }
                                .cornerRadius(8)
                            }
                        }
                    }
                    .buttonStyle(.plain)
                }
            }
        }
    }
}

struct ReviewImageView_Previews: PreviewProvider {
    static var previews: some View {
        GeometryReader { geo in
            ReviewImageView(
                imageUrls: [
                    "https://fulaby-comment.oss-ap-southeast-5.aliyuncs.com/c1fbd653-29fe-4df3-8378-0afeb10b0e94-fulabyFeed.jpeg",
                    "https://fulaby-comment.oss-ap-southeast-5.aliyuncs.com/c1fbd653-29fe-4df3-8378-0afeb10b0e94-fulabyFeed.jpeg"
                ],
                geo: geo,
                onImageClick: { _ in }
            )
        }
    }
}

