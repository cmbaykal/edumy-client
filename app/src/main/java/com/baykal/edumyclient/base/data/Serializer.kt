package com.baykal.edumyclient.base.data

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.modules.SerializersModule
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@Serializer(forClass = Date::class)
object DateSerializer : KSerializer<Date> {
    private val df: DateFormat = SimpleDateFormat("dd.MM.yyyy HH.mm.ss", Locale.ENGLISH)

    override val descriptor = PrimitiveSerialDescriptor("DateSerializer", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Date = df.parse(decoder.decodeString()) ?: Date()

    override fun serialize(encoder: Encoder, value: Date) = encoder.encodeString(df.format(value.time))
}