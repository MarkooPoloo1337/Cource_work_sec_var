package ru.altmanea.edu.server.repo

import java.lang.System.currentTimeMillis
import java.util.concurrent.ConcurrentHashMap

class ListRepo<E> : Repo<E> {
    private val concurrentHashMap = ConcurrentHashMap<String, Pair<E, Long>>()

    override fun get(uuid: String): RepoItem<E>? =
        concurrentHashMap[uuid]?.let {
            RepoItem(it.first, uuid, it.second)
        }

    override fun find(predicate: (E) -> Boolean): List<RepoItem<E>> =
        concurrentHashMap
            .filter { (_, value) -> predicate(value.first) }
            .map {
                RepoItem(it.value.first, it.key, it.value.second)
            }

    override fun findAll(): List<RepoItem<E>> =
        concurrentHashMap
            .map { RepoItem(it.value.first, it.key, it.value.second) }

    @Suppress("KotlinConstantConditions")
    override fun create(element: E): Boolean =
        RepoItem(element).let {
            concurrentHashMap[it.uuid] = it.elem to it.etag
            true
        }

    override fun update(uuid: String, value: E): Boolean =
        if (concurrentHashMap.containsKey(uuid)) {
            concurrentHashMap[uuid] = value to currentTimeMillis()
            true
        } else false

    override fun delete(uuid: String): Boolean =
        concurrentHashMap.remove(uuid) != null


    override fun isEmpty(): Boolean =
        concurrentHashMap.isEmpty()

}