//
//  ApplyAsMentorScreen.swift
//  AjarinIos
//
//  Created by Darren Thiores on 26/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ApplyAsMentorScreen: View {
    @StateObject private var viewModel = IosApplyAsMentorViewModel()
    @Environment(\.dismiss) var dismiss
    
    var body: some View {
        ZStack {
            List {
                Section("Personal") {
                    DefaultTextField(
                        title: "Full Name",
                        text: Binding(
                            get: {
                                viewModel.state.fullName
                            },
                            set: {
                                viewModel.onEvent(
                                    event: .OnFullNameChange(newText: $0)
                                )
                            }
                        )
                    )
                    
                    DefaultTextField(
                        title: "ID",
                        text: Binding(
                            get: {
                                viewModel.state.id
                            },
                            set: {
                                viewModel.onEvent(
                                    event: .OnIdChange(newText: $0)
                                )
                            }
                        )
                    )
                    
                    if let idError = viewModel.state.idError {
                        HStack {
                            Text(idError.description())
                                .font(.caption)
                                .foregroundColor(.red)
                            
                            Spacer()
                        }
                    }
                    
                    ClickableField(
                        value: viewModel.iosState.idFile?.lastPathComponent,
                        title: "Select ID File (As Pdf)",
                        onClick: {
                            viewModel.onEvent(
                                event: .openIdPicker
                            )
                        }
                    )
                    .fileImporter(
                        isPresented: $viewModel.iosState.isSelectingIDFile,
                        allowedContentTypes: [.pdf]
                    ) { result in
                        switch result {
                        case .success(let url):
                            print(url)
                            viewModel.onEvent(
                                event: .selectIdFile(file: url)
                            )
                        case .failure(let error):
                            print(error)
                        }
                    }
                }
                .listRowSeparator(.hidden)
                
                Section("Education") {
                    EducationDropDown(
                        education: viewModel.state.lastEducation,
                        isOpen: viewModel.state.isEducationDropDownOpen,
                        selectEducation: { education in
                            viewModel.onEvent(
                                event: .OnPickEducation(education: education)
                            )
                        }
                    )
                    
                    DefaultTextField(
                        title: "Institution (Optional)",
                        text: Binding(
                            get: {
                                viewModel.state.institutionName
                            },
                            set: {
                                viewModel.onEvent(
                                    event: .OnInstitutionChange(newText: $0)
                                )
                            }
                        )
                    )
                    
                    ClickableField(
                        value: viewModel.iosState.institutionFile?.lastPathComponent,
                        title: "Select Institution ID File (As Pdf) (Optional)",
                        onClick: {
                            viewModel.onEvent(
                                event: .openInstitutionPicker
                            )
                        }
                    )
                    .fileImporter(
                        isPresented: $viewModel.iosState.isSelectingInstitutionFile,
                        allowedContentTypes: [.pdf]
                    ) { result in
                        switch result {
                        case .success(let url):
                            print(url)
                            viewModel.onEvent(
                                event: .selectInstitutionFile(file: url)
                            )
                        case .failure(let error):
                            print(error)
                        }
                    }
                    
                    let value = viewModel.state.selectedCourses.isEmpty ? nil : "Selected \(viewModel.state.selectedCourses.count) Course(s)"
                    
                    ClickableField(
                        value: value,
                        title: "Select Courses",
                        onClick: {
                            viewModel.onEvent(
                                event: .openCoursesSheet
                            )
                        }
                    )
                    
                    ClickableField(
                        value: viewModel.iosState.gradeFile?.lastPathComponent,
                        title: "Grade Report File (As Pdf)",
                        onClick: {
                            viewModel.onEvent(
                                event: .openGradePicker
                            )
                        }
                    )
                    .fileImporter(
                        isPresented: $viewModel.iosState.isSelectingGradeFile,
                        allowedContentTypes: [.pdf]
                    ) { result in
                        switch result {
                        case .success(let url):
                            print(url)
                            viewModel.onEvent(
                                event: .selectGradeFile(file: url)
                            )
                        case .failure(let error):
                            print(error)
                        }
                    }
                }
                .listRowSeparator(.hidden)
                
                Section("Schedule") {
                    let value = viewModel.state.selectedSchedule.isEmpty ? nil : "Selected \(viewModel.state.selectedSchedule.count) Schedule(s)"
                    
                    ClickableField(
                        value: value,
                        title: "Select Schedules",
                        onClick: {
                            viewModel.onEvent(
                                event: .openSchedulesSheet
                            )
                        }
                    )
                }
                .listRowSeparator(.hidden)
                
                Section("Fee") {
                    DefaultTextField(
                        title: "Proposed Fee (Optional)",
                        text: Binding(
                            get: {
                                viewModel.state.proposedFee
                            },
                            set: {
                                viewModel.onEvent(
                                    event: .OnFeeChange(newText: $0)
                                )
                            }
                        )
                    )
                    .keyboardType(.decimalPad)
                }
                .listRowSeparator(.hidden)
            }
            .listStyle(.plain)
            
            VStack {
                Spacer()
                
                HStack {
                    Spacer()
                    
                    Button {
                        viewModel.onEvent(
                            event: .OnApply()
                        )
                    } label: {
                        Text("Apply")
                            .font(.title2)
                            .foregroundColor(Color.white)
                            .padding()
                    }
                    .buttonStyle(.plain)
                    .background(.primary)
                    .cornerRadius(16)
                    .padding()
                    .shadow(
                        color: Color.black.opacity(0.3),
                        radius: 3,
                        x: 3,
                        y: 3
                    )
                }
            }
        }
        .navigationTitle("Apply As Mentor")
        .sheet(isPresented: $viewModel.iosState.isSelectingCourses) {
            SelectCoursesSheet(
                selectedCourses: viewModel.state.selectedCourses,
                onClick: { course in
                    viewModel.onEvent(
                        event: .OnPickCourse(course: course)
                    )
                }
            )
        }
        .sheet(isPresented: $viewModel.iosState.isSelectingSchedules) {
            SelectSchedulesSheet(
                selectedSchedules: viewModel.state.selectedSchedule,
                onClick: { schedule in
                    viewModel.onEvent(
                        event: .OnPickSchedule(schedule: schedule)
                    )
                }
            )
        }
        .onAppear {
            viewModel.startObserving()
        }
        .onDisappear {
            viewModel.dispose()
        }
        .onChange(of: viewModel.state.applySuccess) { result in
            if result {
                dismiss()
            }
        }
    }
}

struct ApplyAsMentorScreen_Previews: PreviewProvider {
    static var previews: some View {
        ApplyAsMentorScreen()
    }
}
