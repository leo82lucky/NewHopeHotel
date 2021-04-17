package com.example.newhopehotel.data


enum class UIState {
    LOADING,
    HAS_DATA,
    EMPTY
}

enum class RoomID {
    R001,
    R002,
    R003,
    R004,
    R005,
    R006,
    R007,
    R008,
    R009,
    R010,
    R011,
    R012,
    R013,
    R014,
    R015,
    R016
}

enum class RoomType {
    Standard,
    Deluxe,
    Luxury
}

enum class RoomStatus {
    Available,
    Unavailable
}

enum class MorningCall(val time: String) {
    NONE("None"),
    EightAM("8 AM"),
    NineAM("9 AM"),
    TenAM("10 AM"),
    ElevenAM("11 AM")
//    NONE,
//    EightAM,
//    NineAM,
//    TenAM,
//    ElevenAM
}