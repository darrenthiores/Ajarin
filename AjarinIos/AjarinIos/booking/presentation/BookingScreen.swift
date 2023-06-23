//
//  BookingScreen.swift
//  AjarinIos
//
//  Created by Darren Thiores on 22/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct BookingScreen: View {
    let id: String
    
    @StateObject private var viewModel = IosBookingViewModel()
    @State private var sessionSheetOpen = false
    @State private var paymentMethodSheetOpen = false
    @State private var date = Date()
    
    @Environment(\.presentationMode) var presentationMode
    
    let converter = LocalDateConverter()
    
    let dateRange: ClosedRange<Date> = {
        let calendar = Calendar.current
        let currentDate = Date()
        let currentYear = calendar.component(.year, from: currentDate)
        let currentMonth = calendar.component(.month, from: currentDate)
        let currentDay = calendar.component(.day, from: currentDate)
        
        let startComponents = DateComponents(year: currentYear, month: currentMonth, day: currentDay)
        let endComponents = DateComponents(year: currentYear + 1, month: currentMonth, day: currentDay)
        return calendar.date(from:startComponents)!
            ...
            calendar.date(from:endComponents)!
    }()
    
    var body: some View {
        List {
            Section("Mentor Detail") {
                SmallMentorCard(mentor: viewModel.state.mentor)
                
                CourseDropDown(
                    courses: viewModel.state.mentor?.courses ?? [],
                    course: viewModel.state.course,
                    isOpen: viewModel.state.isCourseDropDownOpen,
                    selectCourse: { course in
                        viewModel.onEvent(
                            event: .PickCourse(course: course)
                        )
                    }
                )
            }
            .listRowSeparator(.hidden)
            
            Section("Schedule") {
                DatePicker(
                    "Date",
                    selection: Binding(
                        get: {
                            date
                        },
                        set: {
                            date = $0
                            
                            let calendar = Calendar.current
                            let year = calendar.component(.year, from: date)
                            let month = calendar.component(.month, from: date)
                            let day = calendar.component(.day, from: date)
                            
                            let monthText = (month < 9) ? "0\(month)" : "\(month)"
                            let dayText = (day < 9) ? "0\(day)" : "\(day)"
                            
                            viewModel.onEvent(
                                event: .PickDate(
                                    date: converter.toLocalDate(
                                        date: "\(year)-\(monthText)-\(dayText)"
                                    )
                                )
                            )
                        }
                    ),
                    in: dateRange,
                    displayedComponents: [.date]
                )
                
                let sessionText = (viewModel.state.schedule != nil) ?
                "Session \(viewModel.state.schedule?.id ?? "") - \(viewModel.state.schedule?.time ?? "")"
                : nil
                
                ClickableField(
                    value: sessionText,
                    title: "Pick Session",
                    onClick: {
                        sessionSheetOpen = true
                    }
                )
            }
            .listRowSeparator(.hidden)
            
            Section("Payment Method") {
                PaymentMethodField(
                    value: viewModel.state.paymentMethod,
                    title: "Pick Payment Method",
                    onClick: {
                        paymentMethodSheetOpen = true
                    }
                )
            }
            .listRowSeparator(.hidden)
            
            Divider()
                .listRowSeparator(.hidden)
            
            Group {
                HStack {
                    Text("Platform Fee")
                    
                    Spacer()
                    
                    Text("Rp. 0")
                }
                
                HStack {
                    Text("Discount")
                    
                    Spacer()
                    
                    Text("- Rp. 0")
                }
                
                HStack {
                    Text("Mentor Fee")
                    
                    Spacer()
                    
                    Text("Rp. \(viewModel.state.mentor?.price ?? "0")")
                }
            }
            .listRowSeparator(.hidden)
        }
        .listStyle(.plain)
        .navigationTitle("Booking Session")
        .toolbar {
            ToolbarItem(placement: .bottomBar) {
                HStack {
                    Text("Rp. \(viewModel.state.mentor?.price ?? "0")")
                    
                    Spacer()
                    
                    Button {
                        viewModel.onEvent(
                            event: .Book()
                        )
                    } label: {
                        Text("Pay")
                            .frame(width: 100)
                    }
                    .buttonStyle(.borderedProminent)
                }
            }
        }
        .sheet(isPresented: $sessionSheetOpen) {
            ScheduleSheet(
                date: viewModel.state.date,
                onSelect: { session in
                    viewModel.onEvent(
                        event: .PickSchedule(session: session)
                    )
                    
                    sessionSheetOpen = false
                }
            )
        }
        .sheet(isPresented: $paymentMethodSheetOpen) {
            PaymentMethodSheet(
                selectMethod: { method in
                    viewModel.onEvent(
                        event: .PickPaymentMethod(
                            method: method
                        )
                    )
                    
                    paymentMethodSheetOpen = false
                }
            )
        }
        .navigationDestination(
            isPresented: Binding(
                get: {
                    viewModel.state.bookingSuccess
                },
                set: { _ in }
            )
        ) {
            BookingSuccessScreen(
                onBack: {
                    presentationMode.wrappedValue.dismiss()
                }
            )
        }
        .onAppear {
            viewModel.startObserving()
            viewModel.initMentor(id: id)
        }
        .onDisappear {
            viewModel.dispose()
        }
    }
}

struct BookingScreen_Previews: PreviewProvider {
    static var previews: some View {
        BookingScreen(id: "M1")
    }
}
