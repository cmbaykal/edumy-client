package com.baykal.edumyclient.base.data


//@Serializer(forClass = Date::class)
//object DateSerializer : KSerializer<Date> {
//    private val df: DateFormat = SimpleDateFormat("dd.MM.yyyy HH.mm.ss", Locale.ENGLISH)
//
//    override val descriptor = PrimitiveSerialDescriptor("DateSerializer", PrimitiveKind.STRING)
//
//    override fun deserialize(decoder: Decoder): Date = df.parse(decoder.decodeString()) ?: Date()
//
//    override fun serialize(encoder: Encoder, value: Date) = encoder.encodeString(df.format(value.time))
//}