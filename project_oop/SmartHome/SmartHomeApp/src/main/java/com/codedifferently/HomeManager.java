package com.smarthome.app;

import java.util.*;

public class HomeManager {
    private final String accountId;
    private final DeviceRegistry registry;
    private final Set<String> rooms;

    public HomeManager(String accountId) {
        this.accountId = accountId;
        this.registry = new DeviceRegistry();
        this.rooms = new HashSet<>();
    }

    public String getAccountId() { return accountId; }

    public Set<String> getRooms() {
        return Collections.unmodifiableSet(rooms);
    }

    public boolean addRoom(String room) {
        return rooms.add(room);
    }

    public boolean deleteRoom(String room) {
        if (!rooms.contains(room)) return false;
        for (Device d : new ArrayList<>(registry.getDevicesInRoom(room))) {
            registry.remove(d.getDeviceId());
        }
        rooms.remove(room);
        return true;
    }

    public boolean addDevice(Device device, String room) {
        addRoom(room);
        return registry.add(device, room);
    }

    public boolean removeDevice(String deviceId) {
        return registry.remove(deviceId);
    }

    public Optional<Device> getDevice(String deviceId) {
        return Optional.ofNullable(registry.get(deviceId));
    }

    public Collection<Device> getDevices() {
        return registry.getAll();
    }

    public Collection<Device> getDevicesInRoom(String room) {
        return registry.getDevicesInRoom(room);
    }

    static class DeviceRegistry {
        private final Map<String, Device> byId = new HashMap<>();
        private final Map<String, Set<Device>> byRoom = new HashMap<>();

        boolean add(Device device, String room) {
            if (device == null || room == null) return false;
            if (byId.containsKey(device.getDeviceId())) return false;
            byId.put(device.getDeviceId(), device);
            byRoom.computeIfAbsent(room, r -> new HashSet<>()).add(device);
            device.setLinked(true);
            return true;
        }

        boolean remove(String deviceId) {
            Device d = byId.remove(deviceId);
            if (d == null) return false;
            byRoom.values().forEach(set -> set.remove(d));
            d.setLinked(false);
            return true;
        }

        Device get(String deviceId) {
            return byId.get(deviceId);
        }

        Collection<Device> getAll() {
            return Collections.unmodifiableCollection(byId.values());
        }

        Collection<Device> getDevicesInRoom(String room) {
            return Collections.unmodifiableCollection(
                byRoom.getOrDefault(room, Collections.emptySet())
            );
        }
    }
}
