package cz.dusanrychnovsky.util

object ListUtils {
  
  def union[T](lists: List[List[T]]): List[T] = {
    lists.flatten.distinct
  }
}