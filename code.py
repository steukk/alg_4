def greedy_scheduling(machines, jobs):
    machine_loads = [0] * machines
    sorted_jobs = sorted(jobs, reverse=True)
    
    for job in sorted_jobs:
        min_load_index = machine_loads.index(min(machine_loads))
        machine_loads[min_load_index] += job
    
    return machine_loads, max(machine_loads)

def optimal_brute_force(machines, jobs):
    from itertools import product
    
    best_makespan = float('inf')
    best_assignment = None
    
    # Генерируем все возможные распределения
    for assignment in product(range(machines), repeat=len(jobs)):
        machine_loads = [0] * machines
        for job_idx, machine_idx in enumerate(assignment):
            machine_loads[machine_idx] += jobs[job_idx]
        
        current_makespan = max(machine_loads)
        if current_makespan < best_makespan:
            best_makespan = current_makespan
            best_assignment = assignment
    
    return best_assignment, best_makespan

# Входные данные
machines = 3
jobs = [5, 7, 3, 8, 2, 6, 4, 9, 1, 5]

# Жадный алгоритм
greedy_loads, greedy_makespan = greedy_scheduling(machines, jobs)

print(f"Нагрузка машин: {greedy_loads}")
print(f"Максимальное время: {greedy_makespan}")

# Оптимальное решение (для сравнения)
if len(jobs) <= 10:  # Ограничиваем из-за вычислительной сложности
    optimal_assignment, optimal_makespan = optimal_brute_force(machines, jobs)
    print(f"\nОптимальное решение: {optimal_makespan}")
    print(f"Разница: {greedy_makespan - optimal_makespan}")

#Нагрузка машин: [17, 17, 16]
#Максимальное время: 17
#Оптимальное решение: 17
#Разница: 0
