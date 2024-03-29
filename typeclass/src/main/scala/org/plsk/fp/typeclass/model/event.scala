package org.plsk.fp.typeclass.model

import Event._

case class RawEvent(eventType: String, payload: String)

sealed trait DomainEvent {
  def programID: ProgramID
  def userID: UserID
  def createdAt: Long
}

case class ProgramStarted(userID: UserID, programID: ProgramID, createdAt: Long, startedAt: Long) extends DomainEvent

case class ProgramCancelled(userID: UserID, programID: ProgramID, createdAt: Long, cancelledAt: Long) extends DomainEvent

case class ProgramLiked(userID: UserID, programID: ProgramID, createdAt: Long, likedAt: Long) extends DomainEvent

object Event {
  type ProgramID = String
  type UserID = String
}
